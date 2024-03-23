import axios, { AxiosResponse } from 'axios';

const API_URL = 'http://localhost:8089/api';
const CATEGORY_ENDPOINT = '/categories';

export interface Category {
  categoryCode: string;
  name: string;
  categoryDescription: string;
  // Thêm các trường dữ liệu khác của category nếu cần thiết
}

export async function getCategoryList(): Promise<Category[]> {
  try {
    const response: AxiosResponse<Category[]> = await axios.get<Category[]>(`${API_URL}${CATEGORY_ENDPOINT}/`);
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch categories');
  }
}

export async function getCategoryById(categoryId: string): Promise<Category> {
  try {
    const response: AxiosResponse<Category> = await axios.get<Category>(`${API_URL}${CATEGORY_ENDPOINT}/${categoryId}`);
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch category');
  }
}

export async function createNewCategory(newCategoryData: Category, accessToken: string): Promise<Category> {
  try {
    const response: AxiosResponse<Category> = await axios.post<Category>(
      `${API_URL}${CATEGORY_ENDPOINT}/`,
      newCategoryData,
      {
        headers: {
          Authorization: `Bearer ${accessToken}`,
          'Content-Type': 'application/json',
        },
      }
    );

    if (!response.data) {
      throw new Error('Failed to create category');
    }

    return response.data;
  } catch (error) {
    throw new Error('Failed to create category');
  }
}

export const updateCategory = async (categoryId: string, updatedCategoryData: Category, accessToken: string): Promise<Category> => {
  try {
    const response: Response = await fetch(`${API_URL}${CATEGORY_ENDPOINT}/${categoryId}`, {
      method: 'PUT',
      headers: {
        'Authorization': `Bearer ${accessToken}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedCategoryData),
    });

    if (!response.ok) {
      throw new Error('Failed to update category');
    }

    return response.json();
  } catch (error: any) {
    if (error instanceof Error) {
      throw new Error(error.message);
    } else {
      throw new Error('Unknown error occurred');
    }
  }
};
